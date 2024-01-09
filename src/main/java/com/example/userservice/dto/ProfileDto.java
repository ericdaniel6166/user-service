package com.example.userservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.FederatedIdentityRepresentation;
import org.keycloak.representations.idm.UserConsentRepresentation;
import org.keycloak.representations.idm.UserProfileMetadata;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {
    String self;
    String id;
    String origin;
    @JsonIgnore
    Long createdTimestamp;
    LocalDateTime createdDate;
    String username;
    Boolean enabled;
    Boolean totp;
    Boolean emailVerified;
    String firstName;
    String lastName;
    String email;
    String federationLink;
    String serviceAccountClientId;
    Map<String, List<String>> attributes;
    List<CredentialRepresentation> credentials;
    Set<String> disableableCredentialTypes;
    List<String> requiredActions;
    List<FederatedIdentityRepresentation> federatedIdentities;
    List<String> realmRoles;
    Map<String, List<String>> clientRoles;
    List<UserConsentRepresentation> clientConsents;
    Integer notBefore;
    List<String> groups;
    Map<String, Boolean> access;
    UserProfileMetadata userProfileMetadata;
}
