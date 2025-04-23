#!/bin/bash

# Config
DATE=$(date +%F)
BACKUP_DIR="/home/ubuntu/backups"
API_DIR="/home/ubuntu/deployment_cs421/CS421_springboot/"
API_BACKUP="$BACKUP_DIR/api_backup_$DATE.tar.gz"
DB_BACKUP="$BACKUP_DIR/db_backup_$DATE.sql"
DB_NAME="college_cs421"
DB_USER="postgres"
DB_HOST="localhost"

# Ensure backup directory exists
mkdir -p $BACKUP_DIR

# Back up API project files
tar -czf $API_BACKUP $API_DIR

# Back up PostgreSQL database
PGPASSWORD="CodeIsLife" pg_dump -U $DB_USER -h $DB_HOST -F p -f $DB_BACKUP $DB_NAME

# Delete backups older than 7 days
find $BACKUP_DIR -type f -mtime +7 -name "*.tar.gz" -exec rm {} \;
find $BACKUP_DIR -type f -mtime +7 -name "*.sql" -exec rm {} \;

echo "Backup completed on $(date)" >> $BACKUP_DIR/backup.log
