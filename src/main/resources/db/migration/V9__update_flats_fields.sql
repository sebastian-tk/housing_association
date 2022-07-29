ALTER TABLE flats DROP COLUMN type_use;
ALTER TABLE flats ADD available BIT default 1;

UPDATE flats SET available=0;