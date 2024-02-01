package cipher;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class GenerarClaves {

    public void keyGenerator(String folderPath) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            File folder = new File(folderPath);
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    System.out.println("Carpeta creada exitosamente en: " + folderPath);
                } else {
                    System.err.println("Error al crear la carpeta");
                    return;
                }
            }

            saveKeyToFile(folderPath + File.separator + "publicKey.der", keyPair.getPublic().getEncoded());
            saveKeyToFile(folderPath + File.separator + "privateKey.der", keyPair.getPrivate().getEncoded());

            System.out.println("Ficheros de Clave Generados!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveKeyToFile(String filePath, byte[] keyBytes) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(keyBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        GenerarClaves generarClaves = new GenerarClaves();
        generarClaves.keyGenerator("C:\\Cifrado");

    }
}
