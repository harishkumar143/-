package myorg.bouncycastle.crypto.params;

import java.security.SecureRandom;
import myorg.bouncycastle.crypto.KeyGenerationParameters;
import myorg.bouncycastle.crypto.params.GOST3410Parameters;

public class GOST3410KeyGenerationParameters extends KeyGenerationParameters {

   private GOST3410Parameters params;


   public GOST3410KeyGenerationParameters(SecureRandom var1, GOST3410Parameters var2) {
      int var3 = var2.getP().bitLength() - 1;
      super(var1, var3);
      this.params = var2;
   }

   public GOST3410Parameters getParameters() {
      return this.params;
   }
}
