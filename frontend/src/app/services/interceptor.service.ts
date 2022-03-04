import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
  HttpXsrfTokenExtractor,
} from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { BASE_URI } from '../config';

const IGNORE = ['upload-dir'];

@Injectable()
export class BackendCallsInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    let headers = req.headers;
    headers = headers.append('X-Requested-With', 'XMLHttpRequest');

    const isIgnored = IGNORE.some(str => {
      return req.url.includes(str);
    });

    if (isIgnored) {
      req = req.clone({ headers });
    } else {
      req = req.clone({ headers, url: BASE_URI + req.url });
    }

    return next.handle(req).pipe(
      map((event: HttpEvent<any>) => {
        if (
          event instanceof HttpResponse &&
          event.ok &&
          event.status === 200 &&
          event.body &&
          event.body.filter
        ) {
          const data = event.body;
          return event.clone({ body: data });
        }

        return event;
      }),
      catchError((error: HttpEvent<any>) => {
        return throwError(error);
      })
    );
  }
}
