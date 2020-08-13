package configuration;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class Configuration {

    private Properties prop;

    @SneakyThrows
    private void load() {
        prop = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("config/config.properties");
        prop.load(stream);
    }

    public String getValue(Options option) {
        if (prop == null) {
            load();
        }
        return prop.getProperty(option.getValue());
    }

    public enum Options {
        BASE_PATH("base.path"),
        API_KEY("api.key");

        private final String value;

        Options(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }
}
