db = db.getSiblingDB('snackbar');

db.createUser({
    user: "snackbaruser",
    pwd: "snack01",  
    roles: [{ role: "readWrite", db: "snackbar" }]
});

print("User 'snackbaruser' created.");