
## 📍IAM Endpoints

| route               | description
|----------------------|-----------------------------------------------------
| <kbd>POST /api/iam/register</kbd>     | See [request details](#iam-register)
| <kbd>POST /api/iam/login</kbd>     | See [request details](#iam-login)
| <kbd>GET /api/iam/user/{userId}</kbd>     | See [request details](#iam-get-user)
| <kbd>PUT /api/iam/user/{userId}</kbd>     | See [request details](#iam-update-user)
| <kbd>DELETE /api/iam/user/{userId}</kbd>     | See [request details](#iam-delete-user)

<h3 id="iam-register">POST /api/iam/register</h3>

**REQUEST**
```json
{
    "cpf": "string",
    "password": "string",
    "email": "string",
    "name": "string"
}
```

**RESPONSE**
```json
{
    "message": "User registered successfully",
    "userId": "string"
}
```

<h3 id="iam-login">POST /api/iam/login</h3>

**REQUEST**
```json
{
    "cpf": "string",
    "password": "string"
}
```

**RESPONSE**
```json
{
    "token": "string"
}
```

<h3 id="iam-get-user">GET /api/iam/user/{userId}</h3>

**RESPONSE**
```json
{
    "userId": "string",
    "cpf": "string",
    "email": "string",
    "name": "string"
}
```

<h3 id="iam-update-user">PUT /api/iam/user/{userId}</h3>

**REQUEST**
```json
{
    "email": "string",
    "name": "string"
}
```

**RESPONSE**
```json
{
    "message": "User updated successfully"
}
```

<h3 id="iam-delete-user">DELETE /api/iam/user/{userId}</h3>

**RESPONSE**
```json
{
    "message": "User deleted successfully"
}
```
```
