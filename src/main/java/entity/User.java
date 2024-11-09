package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class User implements Serializable {
    private String login;
    private String password;
    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(login);
        result = 31 * result + Objects.hashCode(password);
        return result;
    }
}
