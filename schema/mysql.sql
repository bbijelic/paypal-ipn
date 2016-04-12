-- Drop schema if already exists
DROP SCHEMA IF EXISTS paypal_ipn;

-- Create schema
CREATE SCHEMA paypal_ipn;

-- Use created schema
USE paypal_ipn;

-- Create paypal ipn user
GRANT ALL ON `paypal_ipn`.* to 'paypal_ipn'@'localhost' identified by 'paypal_ipn';

-- Service Group table
CREATE TABLE paypal_notification (
id					int not null auto_increment,
status				varchar(8) not null COMMENT 'Notification status',
transaction_id		varchar(20) not null COMMENT 'Unique transaction id',
transaction_type	varchar(25) not null COMMENT 'Transaction type',
notify_version		varchar(10) not null COMMENT 'Notification version',
received_at			timestamp not null default current_timestamp COMMENT 'Timestamp of when notification was received',
completed_at		timestamp COMMENT 'When was notification validation completed',
raw_parameters		text not null COMMENT 'Raw notification parameters',

PRIMARY KEY (id),
CONSTRAINT paypal_notification_uq UNIQUE (transaction_id)
) ENGINE = MyISAM;