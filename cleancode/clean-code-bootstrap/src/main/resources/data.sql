INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664244', 'COMMON', 'TANK', 'ARMAND', 0, 1);
INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664245', 'RARE', 'ASSASSIN', 'ENZO', 0, 1);
INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664246', 'LEGENDARY', 'MAGE', 'SID', 0, 1);
INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664247', 'COMMON', 'ASSASSIN', 'ENZO', 0, 1);
INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664248', 'RARE', 'MAGE', 'DENIS', 0, 1);
INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664249', 'LEGENDARY', 'TANK', 'REMY', 0, 1);
INSERT INTO cards (card_reference, card_rarity, card_specialty, card_name, xp, level) VALUES ('123e4567-e89b-42d3-a456-55664250', 'COMMON', 'MAGE', 'ARNAUD', 0, 1);

INSERT INTO card_collections (card_collection_id, card_collection_reference, card_collection_name) VALUES (1, '223e4567-e89b-42d3-a456-55664244', 'user1');
INSERT INTO card_collections (card_collection_id, card_collection_reference, card_collection_name) VALUES (2, '223e4567-e89b-42d3-a456-55664245', 'user2');

INSERT INTO users (user_reference, username, cccoin_wallet, win_count, creation_date, card_collection_id) VALUES ('323e4567-e89b-42d3-a456-55664241', 'user1', 4, 1, '2020-01-01', 1);
INSERT INTO users (user_reference, username, cccoin_wallet, win_count, creation_date, card_collection_id) VALUES ('323e4567-e89b-42d3-a456-55664242', 'user2', 4, 2, '2020-01-01', 2);



