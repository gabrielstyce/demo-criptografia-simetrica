import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CryptoService } from './crypto/crypto.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  public plainText!: string;
  public encrytedText!: string;
  public decyptedText!: string;
  public fromServer: any = null;

  public get decryptSuccess(): boolean {
    return this.plainText === this.decyptedText;
  }

  constructor(
    private readonly cryptoService: CryptoService,
    private readonly http: HttpClient
  ) { }

  private transformResposta(resposta: any) {
    this.fromServer = {
      text1: {
        encrypted: resposta.text1,
        decrypted: this.cryptoService.decypt(resposta.text1)
      },
      text2: {
        encrypted: resposta.text2,
        decrypted: this.cryptoService.decypt(resposta.text2)
      }
    }
  }

  private handleError(error: any) {
    console.error(error);
    return throwError(() => new Error(error || 'Server error'));
  }

  encrypt() {
    this.fromServer = null;
    const encryptedText = this.cryptoService.encrypt(this.plainText);
    this.encrytedText = encryptedText;
    console.log(encryptedText);
  }

  decrypt() {
    const url = `${environment.cryptoUrl}/AES/decrypt`;
    let body = JSON.stringify({
      text: this.encrytedText
    });
    let headers = new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('x-app-private-key', this.cryptoService.key);

    return this.http.post(url, body, {
      headers: headers,
      observe: 'body'
    })
      .pipe(
        catchError(this.handleError)
      )
      .subscribe(
        (response) => {
          console.log(response);

          if (response) {
            this.decyptedText = (response as any).text;
            this.transformResposta(response);
          }
        }
      );
  }
}
