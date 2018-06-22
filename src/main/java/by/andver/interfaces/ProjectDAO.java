package by.andver.interfaces;

import by.andver.objects.Project;

public interface ProjectDAO {
    void saveProject(Project project);
    Project findProjectById(Integer id);
    void deleteProject (Project project);
}
