# Cybersport CRM

## How to run

1. mvn `spring-boot:run`

OR

1. Package the app with your favorite tool and run `CsportApplication` as main class

## How to setup environment

You can specify spring profiles with `--spring.profiles.active=` cmd line parameter,
or `spring.profiles.active` environment variable

See [the docs](https://docs.spring.io/spring-boot/docs/current/reference/html/howto-properties-and-configuration.html) for more detail.

### DB setup

Add `h2` to active profiles to run with disposable h2 DB. Otherwise, specify
`spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` as cmd line or env variables.