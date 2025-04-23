#!/bin/bash

LOG_FILE="/var/log/server_health.log"
TIMESTAMP=$(date '+%Y-%m-%d %H:%M:%S')

# Logging function
log() {
    echo "$TIMESTAMP - $1" | sudo tee -a "$LOG_FILE" > /dev/null
}

log "===== Server Health Check Started ====="

CPU=$(top -bn1 | grep "Cpu(s)")
log "CPU Usage: $CPU"

MEMORY=$(free -m)
log "Memory Usage: $MEMORY"

DISK=$(df -h / | awk 'NR==2 {print $5}')
log "Disk Usage: $DISK"
USAGE=$(echo "$DISK" | tr -d '%')
if [ "$USAGE" -ge 90 ]; then
    log "WARNING: Disk usage is above 90%!"
fi

NGINX_STATUS=$(systemctl is-active nginx)
log "Nginx Status: $NGINX_STATUS"
if [ "$NGINX_STATUS" != "active" ]; then
    log "WARNING: Nginx is not running!"
fi

STUDENTS_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://16.16.207.108:8080/api/v1/student/all)
log "Students Endpoint Status: HTTP $STUDENTS_STATUS"
if [ "$STUDENTS_STATUS" -ne 200 ]; then
    log "WARNING: /api/v1/student/all endpoint is down or not returning 200 OK!"
fi

SUBJECTS_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://16.16.207.108:8080/api/v1/subject/all)
log "Subjects Endpoint Status: HTTP $SUBJECTS_STATUS"
if [ "$SUBJECTS_STATUS" -ne 200 ]; then
    log "WARNING: /api/v1/subject/all endpoint is down or not returning 200 OK!"
fi

log "===== Server Health Check Completed ====="

