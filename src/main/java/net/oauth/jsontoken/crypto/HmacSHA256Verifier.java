/**
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package net.oauth.jsontoken.crypto;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.Arrays;

/**
 * A {@link Verifier} that uses HMAC-SHA256 to verify symmetric-key signatures on byte arrays.
 */
public class HmacSHA256Verifier implements Verifier {

  private final HmacSHA256Signer signer;

  /**
   * Public constructor.
   * @param verificationKey the HMAC verification key to be used for signature verification.
   * @throws InvalidKeyException if the verificationKey cannot be used as an HMAC key.
   */
  public HmacSHA256Verifier(byte[] verificationKey) throws InvalidKeyException {
    signer = new HmacSHA256Signer("verifier", null, verificationKey);
  }

  /*
   * (non-Javadoc)
   * @see net.oauth.jsontoken.crypto.Verifier#verifySignature(byte[], byte[])
   */
  @Override
  public void verifySignature(byte[] source, byte[] signature) throws SignatureException {
    byte[] comparison = signer.sign(source);
    if (!Arrays.equals(comparison, signature)) {
      throw new SignatureException("signature did not verify");
    }
  }
}
