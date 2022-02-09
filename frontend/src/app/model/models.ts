export interface CodeTask {
  name: string;
  code: string;
  description: string;
}

export interface SubmitTask {
  userName: string;
  taskName: string;
  code: string;
}

export interface User {
  name: string;
  tasks: CodeTask[];
  score: number;
}

export interface Result {
  users: User[];
  total: number;
}
