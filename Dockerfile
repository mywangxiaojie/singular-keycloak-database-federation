FROM quay.io/keycloak/keycloak:17.0.1 as builder

ADD ./dist  /opt/keycloak/providers
ENV KC_DB=mysql
RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:17.0.1
COPY --from=builder /opt/keycloak/ /opt/keycloak/
WORKDIR /opt/keycloak

EXPOSE 8443
EXPOSE 8080

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start"]