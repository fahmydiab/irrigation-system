import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable, of} from "rxjs";
import {Plot} from "../models/plot.model";
import {catchError, tap} from "rxjs/operators";
import {BASE_URI} from "../config";

const CONF = {
  name: 'plot',
  url: 'plots',
  baseUrl: '/plots',
};
const base_url = BASE_URI+CONF.baseUrl;

@Injectable({
  providedIn: 'root',
})
export class PlotService {

  constructor(private http: HttpClient) {}

  getOneWithDetails(id: number, options= {}): Observable<Plot | null> {
    const url = `${base_url}/${id}`;
    return this.http.get<Plot>(url,options)
      .pipe(catchError(() => of(null)));
  }

  listDetails(options: any = { params: {} }): Observable<Plot[]> {
    return this.http
      .get(base_url, options)
      .pipe(
        catchError( () => of([]))
      ) as Observable<Plot[]>;
  }

  create(plot: Plot) {
    return this.http.post(base_url,plot)
      .pipe(
        catchError(err => {
          throw new Error(err.error)
        })
      );
  }

  update(plot:Plot){
    return this.http.put(base_url,plot)
      .pipe(
        catchError(err => {
          throw new Error(err.error)
        })
      );
  }

  delete<T>(id: number): Observable<Plot> {
    const url = `${base_url}/${id}`;
    return this.http.delete<Plot>(url).pipe(
      catchError(err => {
        throw new Error(err.error);
      })
    );
  }

}
