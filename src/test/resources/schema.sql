CREATE TABLE IF NOT EXISTS  projects (
    id VARCHAR(255) PRIMARY KEY NOT NULL,
    title VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    github_url VARCHAR(255),
    deployment_url VARCHAR(255),
    demo_url VARCHAR(255),
    thumbnail_url VARCHAR(255),
    tech_stack VARCHAR(255) ARRAY,
    date_of_genesis DATE
);