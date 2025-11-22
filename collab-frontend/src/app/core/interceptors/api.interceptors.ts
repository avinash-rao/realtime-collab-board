import { HttpInterceptorFn } from '@angular/common/http';

export const apiInterceptor: HttpInterceptorFn = (req, next) => {

  // If URL already full, leave it (WebSocket or absolute URLs)
  if (req.url.startsWith('http')) {
    return next(req);
  }

  const apiReq = req.clone({
    url: `http://localhost:8080/api/${req.url}`
  });

  return next(apiReq);
};
