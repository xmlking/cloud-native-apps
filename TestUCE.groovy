import javax.crypto.Cipher;

println "Unlimited cryptography enabled: " + (Cipher.getMaxAllowedKeyLength("RC5") >= 256)
