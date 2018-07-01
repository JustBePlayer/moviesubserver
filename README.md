# Movie Subtitle Server

## Project Summary

The idea for the project come from the lack of translated subtitles provided after a new movie premier. It's target is to provide a platform
for translating subtitles to different languages. Many people can take part in translating proccess. Each subtitle can be translated from/to
any language.
The project is not finished yet. By now only managing users, movies and quering translated subtitles is supported.

## Project Structure

The REST API provides the following endpoints
1. **/users** - (_GET | POST | PUT |DELETE_)
2. **/movies** - (_GET | POST | PUT |DELETE)
3. **/subtitles** - fetch translated - (all | by providerId | by movieId | with full content)

Subtitles which are under translation are stored in database
Subtitles which are translated are stored in cloud storage

### Technologies
**Maven** is choosed for build and dependency environment
REST API is created with Spring Boot

REST API contains 2 modules (_applicatin and integration-test_)
Postgres is choosed for database
Docker compose configuration is provided for with database configuration

### Implementation

Pojos with JPA and Jackson mappings are provided for the following enitites: (_User, Movie, Subtitle, SubtitleLine_)
Environment for supporting multiple Cloud storages for managing translaed subtitle files is providid
Depending on conviguration property different cloud storage provider can be applied
By now only GoogleDrive is supported

Depending on the entity attributes the server provide JAVA API for building queries compliant to GoogleDrive API

### Integration tests:
User the maven profile _integration-tests_ to start them

Every time the profile is started new docker container with configured postgres is created and started.
After finishing the container is removed
