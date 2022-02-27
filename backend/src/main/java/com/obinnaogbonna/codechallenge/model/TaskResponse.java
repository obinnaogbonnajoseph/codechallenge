package com.obinnaogbonna.codechallenge.model;

import com.obinnaogbonna.codechallenge.entity.Task;
import com.obinnaogbonna.codechallenge.util.CodeLanguage;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TaskResponse extends TaskRequest {

    private TaskResponse(TaskResponseBuilder builder) {
        super(builder.name, builder.type, builder.code, builder.description);
    }

    public static class TaskResponseBuilder {
        @Setter
        private String name;

        @Setter
        private CodeLanguage type;

        @Setter
        private String code;

        @Setter
        private String description;

        public TaskResponseBuilder(Task task) {
            this.name = task.getName();
            this.type = task.getType();
            this.code = task.getStarterCode();
            this.description = task.getDescription();
        }

        public TaskResponse build() {
            return new TaskResponse(this);
        }
    }
}
