package executor.service.security.authorization;

import executor.service.exception.security.AuthorizationException;

public interface TokenBasedAuthorization {
    /**
     * Authorizes the user if the token is valid.
     *
     * @param jwtToken The JWT token to be verified and used for user authentication.
     * @throws AuthorizationException If there is an error during token verification or authentication.
     *                                This includes TokenExpiredException if the token has expired,
     *                                JWTDecodeException if there is an error decoding the token,
     *                                or SignatureVerificationException if the token signature is invalid.
     */
    void authorizeIfTokenValid(String jwtToken);
}
