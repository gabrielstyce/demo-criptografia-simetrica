import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class CryptoService {
  public readonly key = 'bbef09b53c2f459ab086e741c473ab00';

  public get parsedKey() {
    return CryptoJS.enc.Latin1.parse(this.key);
  }

  public get ivObj() {
    return {
      iv: CryptoJS.enc.Latin1.parse('5617118481967501'),
      mode: CryptoJS.mode.CBC,
      padding: CryptoJS.pad.Pkcs7
    };
  }

  encrypt(text: string) {
    return CryptoJS.AES.encrypt(text, this.parsedKey, this.ivObj).toString();
  }

  decypt(text: string) {
    return CryptoJS.AES.decrypt(text, this.parsedKey, this.ivObj).toString(CryptoJS.enc.Utf8);
  }
}
