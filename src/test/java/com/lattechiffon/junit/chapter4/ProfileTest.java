package com.lattechiffon.junit.chapter4;

import com.lattechiffon.junit.chapter2.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    // 다수의 케이스를 별도의 테스트 메서드로 분리해야 함.
    // 따라서 이 테스트 메서드처럼 여러 개의 케이스가 분리되지 않게 작성하면 안 됨.

    @Test
    public void matches() {
        Profile profile = new Profile("lattechiffon, Inc.");
        Question question = new BooleanQuestion(1, "Got milk?");

        profile.add(new Answer(question, Bool.FALSE));
        Criteria criteria = new Criteria();
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));

        assertFalse(profile.matches(criteria));

        profile.add(new Answer(question, Bool.FALSE));
        criteria = new Criteria();
        criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));

        assertTrue(profile.matches(criteria));
    }
}
