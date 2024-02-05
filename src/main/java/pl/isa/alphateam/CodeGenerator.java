package pl.isa.alphateam;

import java.util.UUID;

public class CodeGenerator {

    public static String getRegistrationCode() {
        UUID uuid = UUID.randomUUID();
        String registrationCode = uuid.toString();
        return registrationCode;
    }

    public static void displayCode(String code) {
        System.out.println("Twój kod rejestracyjny to: " + code);
    }


}