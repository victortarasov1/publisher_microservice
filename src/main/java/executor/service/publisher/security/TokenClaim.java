package executor.service.publisher.security;

public enum TokenClaim {
    ROLES("roles");
    private final String claim;

    TokenClaim(String claim) {
        this.claim = claim;
    }
    public String getClaim() {
        return claim;
    }
}
