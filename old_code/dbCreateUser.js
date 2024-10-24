//use admin
db = connect( 'mongodb://localhost:27017/admin' ); // might be wrong

db.createUser(
  {
    user: '$MONGODB_USER',
    pwd: '$MONGODB_PASSWORD', 
    roles: [
      { role: 'readWrite', db: "$APP_DB" }
    ]
  }
)
