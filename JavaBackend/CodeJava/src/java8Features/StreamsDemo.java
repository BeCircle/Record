package java8Features;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8)
        );
// Calculate total points of all active tasks using sum()
        final long totalPointsOfOpenTasks = tasks
                .stream()// 转换为stream表示
                // filter掉CLOSED的元素
                .filter(task -> task.getStatus() == Status.OPEN)
                // 将每个元素映射成对应的point
                .mapToInt(Task::getPoints)
                .sum();// 求和
        System.out.println("Total points: " + totalPointsOfOpenTasks);

        // Calculate total points of all tasks
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map(Task::getPoints)
                .reduce( 0, Integer::sum );
        System.out.println( "Total points (all tasks): " + totalPoints );

        // Group tasks by their status
        final Map< Status, List< Task >> map = tasks
                .stream()
                .collect( Collectors.groupingBy( Task::getStatus ) );
        System.out.println( map );

        // Calculate the weight of each tasks (as percent of total points)
        final Collection< String > result = tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points / totalPoints )   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weight -> ( long )( weight * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >

        System.out.println( result );
    }

}

enum Status {
    OPEN, CLOSED
}

final class Task {
    private final Status status;
    private final Integer points;

    Task(final Status status, final Integer points) {
        this.status = status;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", status, points);
    }
}


