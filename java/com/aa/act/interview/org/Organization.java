package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

    private Position root;
    
    public Organization() {
        root = createOrganization();
    }
    
    protected abstract Position createOrganization();
    
    public Optional<Position> hire(Name person, String title) {
    return hireHelper(root, person, title);
}

private Optional<Position> hireHelper(Position position, Name person, String title) {
    // Check if the current position's title matches the title we are hiring for
    if (position.getTitle().equals(title) && !position.isFilled()) {
        // Create a new Employee and set their name and identifier
        Employee employee = new Employee(generateEmployeeIdentifier(), person);
        position.setEmployee(Optional.of(employee));
        return Optional.of(position);
    } else {
        // Recursively search for the correct position in the direct reports
        for (Position directReport : position.getDirectReports()) {
            Optional<Position> hiredPosition = hireHelper(directReport, person, title);
            if (hiredPosition.isPresent()) {
                return hiredPosition;
            }
        }
    }
    return Optional.empty();
}

// A helper method to generate a unique identifier for each Employee
private int employeeIdentifier = 1;

private int generateEmployeeIdentifier() {
    return employeeIdentifier++;
}

    public Optional<Position> hire(Name person, String title) {
        //your code here
        return Optional.empty();
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }
    
    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
