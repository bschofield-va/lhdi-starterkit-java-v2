CREATE TABLE facilityvisit.facilityvisit (
    id VARCHAR(128) NOT NULL PRIMARY KEY,
    facilityId VARCHAR NOT NULL,
    type VARCHAR NOT NULL,
    visitorIcn VARCHAR NOT NULL,
    lastName VARCHAR NOT NULL
);
