//use admin
db = connect( 'mongodb://localhost:27017/admin' ); // might be wrong

db.createUser(
  {
    user: "mongodbadmin",
    pwd: passwordPrompt(), // or cleartext password
    roles: [
      { role: "userAdminAnyDatabase", db: "admin" },
      { role: "readWriteAnyDatabase", db: "admin" }
    ]
  }
)
