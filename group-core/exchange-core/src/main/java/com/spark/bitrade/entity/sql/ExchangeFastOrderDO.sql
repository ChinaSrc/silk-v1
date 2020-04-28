-- auto Generated on 2019-03-27 11:22:29 
-- DROP TABLE IF EXISTS `exchange_fast_order`;
CREATE TABLE exchange_fast_order(
    `order_id` BIGINT NOT NULL COMMENT '����ID',
    `member_id` BIGINT NOT NULL DEFAULT 1 COMMENT '���˻�ID',
    `base_symbol` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '���һ��ұ�������,��CNYT��BT',
    `coin_symbol` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '���ұ������ƣ���BTC��LTC',
    `amount` DECIMAL(24,8) NOT NULL DEFAULT 0 COMMENT '�����������������û����������',
    `traded_amount` DECIMAL(24,8) NOT NULL DEFAULT 0 COMMENT '�ɽ��������������ҹ������õ��ĳɽ�����',
    `direction` INTEGER(12) NOT NULL DEFAULT 0 COMMENT '��������:����/����',
    `adjust_rate` DECIMAL(8,4) NOT NULL DEFAULT 0 COMMENT '���ҵ���������ȡֵ[0-1]���������ݣ���¼�ɽ�ʱ����ʵʱ���ʼ۸�ĵ�������',
    `current_price` DECIMAL(24,8) NOT NULL DEFAULT 0 COMMENT 'ʵʱ���ʼۣ���ʵʱ�۸񣩣��������ݣ���¼�ɽ���ʱ��ʵʱ����',
    `traded_price` DECIMAL(24,8) NOT NULL DEFAULT 0 COMMENT '�ɽ��ۣ�����ʵʱ���ʡ����Ҹ��������Լ������������ĳɽ���',
    `initiator_status` INTEGER(12) NOT NULL DEFAULT 0 COMMENT '�һ����𷽴���״̬��0=TRADING�������У�/1=COMPLETED(���)',
    `receiver_status` INTEGER(12) NOT NULL DEFAULT 0 COMMENT '�һ����շ�����״̬��0=TRADING�������У�/1=COMPLETED(���)',
    `create_time` BIGINT NOT NULL DEFAULT 1 COMMENT '�µ�ʱ��',
    `completed_time` BIGINT NOT NULL DEFAULT 1 COMMENT '�ɽ�ʱ��',
    `virtual_brokerage_fee` DECIMAL(24,8) NOT NULL DEFAULT 0 COMMENT '����Ӷ���������ݣ������˻���õ���������=���㲢��¼����ʵʱ���ʺ͵�����Ļ��ʼ���������������档',
    `receive_id` BIGINT NOT NULL DEFAULT 1 COMMENT '�һ����շ��û�ID',
    `app_id` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '����',
    PRIMARY KEY (`order_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '���Ҷ���';
