package asembly.event;

import asembly.event.types.UserEventType;

public record UserEvent(UserEventType type, String user_id) {
}
