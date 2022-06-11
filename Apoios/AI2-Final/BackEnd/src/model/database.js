var Sequelize = require('sequelize');
const db = new Sequelize(
'dbufss95jh7hqq',
'lplhjqwbkcvjap',
'e788277fd342b0e2239f8e698b9d630caca4cdca67a3f4a6f838293819ac16a4',
{
host: 'ec2-107-21-10-179.compute-1.amazonaws.com',
port: '5432',
dialect: 'postgres',
dialectOptions: {
    ssl: {
    rejectUnauthorized: false
    }
    }
}
);

module.exports = db;