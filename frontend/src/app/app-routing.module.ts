import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResultComponent } from 'src/app/components/result/result.component';
import { SolveComponent } from 'src/app/components/solve/solve.component';

const routes: Routes = [
  {
    path: 'solve',
    component: SolveComponent
  },
  {
    path: 'result',
    component: ResultComponent
  },
  {
    path: '',
    redirectTo: 'solve',
    pathMatch: 'full',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
