package com.picpay.picpaychallenge.factory;

import com.picpay.picpaychallenge.entity.Document;
import com.picpay.picpaychallenge.entity.User;
import com.picpay.picpaychallenge.enumerated.UserType;

public class UserFactory {

    public static User createCommonUserWithoutWallet() {
        final Document document = new Document("76599342019", "44125012000118");
        return new User(
                null,
                "Carolina",
                "Herrera",
                document,
                "carolina.herrera@icloud.com",
                "$2a$12$ukfYx9tTmuhvoDj6YhqM/emi0nqY6a2HA3swRsworSv7.OOad9Jr.",
                UserType.COMMON,
                null,
                null,
                null
        );
    }

}
