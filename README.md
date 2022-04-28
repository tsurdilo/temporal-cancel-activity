# io.temporaldemo:temporal-cancel-activity:1.0-SNAPSHOT Project

One Paragraph of project description goes here


To run the created greeting workflow you have to have the Temporal server installed.
Easy way is to do that with [Docker Compose](https://docs.docker.com/compose/gettingstarted/).

1. Start the Temporal Server

```
git clone git@github.com:temporalio/docker-compose.git
cd docker-compose
docker-compose -f docker-compose-cas-es.yml up
```

2. Run the Worker

3. Run the Starter