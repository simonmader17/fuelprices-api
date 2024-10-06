# Fuelprices API

This is the API in [my Fuelprices application stack](https://github.com/simonmader17/fuelprices-svelte?tab=readme-ov-file#description).

## Docker

### `docker-compose.yml`

```yaml
name: fuelprices-api
services:
  db:
    image: postgres:15
    container_name: fuelprices-postgres
    restart: always
    environment:
      - POSTGRES_USER=fuelprices
      - POSTGRES_PASSWORD=fuelprices
      - PGDATA=/var/lib/postgresql/data
    volumes:
      - fuelprices-postgres-data:/var/lib/postgresql/data
  fuelprices-api:
    image: simonmader17/fuelprices-api:latest
    container_name: fuelprices-api
    restart: always
    ports:
      - 30011:30011

volumes:
  fuelprices-postgres-data:
```

Or, if you want to use an external PostgreSQL database:

```yaml
name: fuelprices-api
services:
  fuelprices-api:
    image: simonmader17/fuelprices-api:latest
    container_name: fuelprices-api
    restart: always
    environment:
      - DATABASE_URL="<postgres-db-address:port>"
      - DATABASE_USERNAME="<postgres-username>"
      - DATABASE_PASSWORD="<postgres-password>"
    ports:
      - 30011:30011
```

### Fuelprices Application Stack

This is my complete Fuelprices application stack, consisting of [Web UI](https://github.com/simonmader17/fuelprices-svelte) + [API](https://github.com/simonmader17/fuelprices-api) + [Telegram Bot](https://github.com/simonmader17/fuelprices-bot):

```yaml
name: fuelprices
services:
  fuelprices-svelte:
    image: simonmader17/fuelprices-svelte:latest
    container_name: fuelprices-svelte
    restart: always
    ports:
      - 30012:30012
  fuelprices-api:
    image: simonmader17/fuelprices-api:latest
    container_name: fuelprices-api
    restart: always
    environment:
      - DATABASE_URL="<postgres-db-address:port>"
      - DATABASE_USERNAME="<postgres-username>"
      - DATABASE_PASSWORD="<postgres-password>"
    ports:
      - 30011:30011
  fuelprices-bot:
    image: simonmader17/fuelprices-bot:latest
    container_name: fuelprices-bot
    restart: always
    environment:
      - TOKEN="<telegram-bot-token>"
```
