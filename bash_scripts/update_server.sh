#!/bin/bash

LOG_FILE="/var/log/update.log"
REPO_DIR="/home/ubuntu/deployment_cs421/"
SERVICE_NAME="deployment_task"

# Logging function
log() {
    TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')
    echo "[$TIMESTAMP] $1" >> $LOG_FILE
}

log "Starting update process..."

# Step 1: Update system packages
log "Updating system packages..."
apt update && apt upgrade -y >> $LOG_FILE 2>&1

# Step 2: Pull latest changes from GitHub
log "Pulling latest code from GitHub..."
cd $REPO_DIR || { log "ERROR: Cannot find repo directory."; exit 1; }

git pull >> $LOG_FILE 2>&1
if [ $? -ne 0 ]; then
    log "ERROR: Git pull failed. Aborting update."
    exit 1
fi
# Step 3: Restart API/web server
log "Restarting service: $SERVICE_NAME..."
systemctl restart $SERVICE_NAME >> $LOG_FILE 2>&1

if [ $? -eq 0 ]; then
    log "Update and restart completed successfully."
else
    log "WARNING: Service restart failed."
fi

echo "" >> $LOG_FILE
