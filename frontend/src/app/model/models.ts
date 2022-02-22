export interface CodeTask {
  name: string;
  code: string;
  type: string;
  description: string;
}

export interface SubmitTask {
  userName: string;
  taskName: string;
  code: string;
  type: string;
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
