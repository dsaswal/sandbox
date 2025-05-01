package self.dsa.config.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    private static final Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);

    public Optional<String> getCurrentAuditor() {
            // Get request attributes
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return Optional.of("defaultUserSystem"); // Fallback for non-request contexts (e.g., H2 console)
            }

            logger.info("Attributes value" + attributes);
            // Read userId header
            String userId = attributes.getRequest().getHeader("userId");
            logger.info("userId value" + userId);
            if (userId == null || userId.trim().isEmpty()) {
                return Optional.of("defaultUserSystem"); // Fallback if header is missing
            }

            return Optional.of(userId);
        }
}