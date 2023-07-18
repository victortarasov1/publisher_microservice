package executor.service.publisher.enums;

public enum Claim {
    ROLES("roles"),
    CREDENTIALS("credentials");
    private final String claim;

    Claim(String claim) {
        this.claim = claim;
    }
    public String getClaim() {
        return claim;
    }
}
