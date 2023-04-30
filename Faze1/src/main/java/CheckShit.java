import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CheckShit {

    // Function to hash a password with a random salt
    public static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        // Concatenate the salt and password
        byte[] saltedPassword = new byte[salt.length + password.getBytes().length];
        System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
        System.arraycopy(password.getBytes(), 0, saltedPassword, salt.length, password.getBytes().length);

        // Hash the salted password using SHA256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(saltedPassword);
        byte[] hashedPassword = messageDigest.digest();

        // Convert the hashed password to a hexadecimal string and return it
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashedPassword) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

    // Function to verify a password against a stored hash and salt
    public static boolean verifyPassword(String password, String storedHash, byte[] storedSalt) throws NoSuchAlgorithmException {
        // Hash the entered password using the stored salt
        String enteredHash = hashPassword(password, storedSalt);

        // Compare the entered hash to the stored hash
        return enteredHash.equals(storedHash);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Example usage
        String password = "my_password123";
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[32];
        secureRandom.nextBytes(salt);

        String hashedPassword = hashPassword(password, salt);

        // Store the hashed password and salt in a database
        // ...

        // When the user logs in, retrieve their stored hash and salt from the database
        // ...

        // Verify the user's password
        if (verifyPassword("my_password123", hashedPassword, salt)) {
            System.out.println("Password is correct!");
        } else {
            System.out.println("Password is incorrect.");
        }
    }
}