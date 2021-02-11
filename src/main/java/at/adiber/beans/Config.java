package at.adiber.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Config {
    private String token;
    private int timeout;
}
