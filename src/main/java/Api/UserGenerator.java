package Api;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User andomUser() {
        return new User(RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru", RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10));
    }

}
