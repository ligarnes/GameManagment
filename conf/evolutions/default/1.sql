# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table campaign (
  campaign_id               integer auto_increment not null,
  constraint pk_campaign primary key (campaign_id))
;

create table campaign_conversation (
  conversation_id           integer auto_increment not null,
  campaign_campaign_id      integer not null,
  title                     varchar(255),
  constraint pk_campaign_conversation primary key (conversation_id))
;

create table campaign_game_session (
  game_session_id           integer auto_increment not null,
  campaign_campaign_id      integer not null,
  is_validate               tinyint(1) default 0,
  date                      varchar(255),
  duration                  varchar(255),
  constraint pk_campaign_game_session primary key (game_session_id))
;

create table campaign_game_session_player_state (
  game_session_player_state_id integer auto_increment not null,
  player_id                 integer,
  status                    integer,
  game_session_game_session_id integer,
  constraint ck_campaign_game_session_player_state_status check (status in (0,1,2)),
  constraint pk_campaign_game_session_player_state primary key (game_session_player_state_id))
;

create table campaign_conversation_message (
  message_id                integer auto_increment not null,
  conversation_conversation_id integer not null,
  author_id                 integer,
  publish_date              varchar(255),
  message                   TEXT,
  constraint pk_campaign_conversation_message primary key (message_id))
;

create table campaign_player (
  player_id                 integer auto_increment not null,
  campaign_campaign_id      integer not null,
  real_user_id              integer,
  color                     varchar(255),
  is_manager                tinyint(1) default 0,
  constraint pk_campaign_player primary key (player_id))
;

create table user (
  user_id                   integer auto_increment not null,
  email                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  image_path                varchar(255),
  name                      varchar(255),
  surname                   varchar(255),
  phone_home                varchar(255),
  phone_cell                varchar(255),
  phone_other               varchar(255),
  address                   varchar(255),
  last_connection           varchar(255),
  constraint pk_user primary key (user_id))
;

alter table campaign_conversation add constraint fk_campaign_conversation_campaign_1 foreign key (campaign_campaign_id) references campaign (campaign_id) on delete restrict on update restrict;
create index ix_campaign_conversation_campaign_1 on campaign_conversation (campaign_campaign_id);
alter table campaign_game_session add constraint fk_campaign_game_session_campaign_2 foreign key (campaign_campaign_id) references campaign (campaign_id) on delete restrict on update restrict;
create index ix_campaign_game_session_campaign_2 on campaign_game_session (campaign_campaign_id);
alter table campaign_game_session_player_state add constraint fk_campaign_game_session_player_state_player_3 foreign key (player_id) references campaign_player (player_id) on delete restrict on update restrict;
create index ix_campaign_game_session_player_state_player_3 on campaign_game_session_player_state (player_id);
alter table campaign_game_session_player_state add constraint fk_campaign_game_session_player_state_gameSession_4 foreign key (game_session_game_session_id) references campaign_game_session (game_session_id) on delete restrict on update restrict;
create index ix_campaign_game_session_player_state_gameSession_4 on campaign_game_session_player_state (game_session_game_session_id);
alter table campaign_conversation_message add constraint fk_campaign_conversation_message_campaign_conversation_5 foreign key (conversation_conversation_id) references campaign_conversation (conversation_id) on delete restrict on update restrict;
create index ix_campaign_conversation_message_campaign_conversation_5 on campaign_conversation_message (conversation_conversation_id);
alter table campaign_conversation_message add constraint fk_campaign_conversation_message_author_6 foreign key (author_id) references campaign_player (player_id) on delete restrict on update restrict;
create index ix_campaign_conversation_message_author_6 on campaign_conversation_message (author_id);
alter table campaign_player add constraint fk_campaign_player_campaign_7 foreign key (campaign_campaign_id) references campaign (campaign_id) on delete restrict on update restrict;
create index ix_campaign_player_campaign_7 on campaign_player (campaign_campaign_id);
alter table campaign_player add constraint fk_campaign_player_user_8 foreign key (real_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_campaign_player_user_8 on campaign_player (real_user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table campaign;

drop table campaign_conversation;

drop table campaign_game_session;

drop table campaign_game_session_player_state;

drop table campaign_conversation_message;

drop table campaign_player;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

