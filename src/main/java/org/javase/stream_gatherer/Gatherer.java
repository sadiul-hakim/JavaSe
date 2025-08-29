//import org.javase.stream_gatherer.BlogPost;
//import org.javase.stream_gatherer.GathererUtil;
//
//void main() {
//    var posts = GathererUtil.createSampleBlogPosts();
//
//    // Old Stream
//    postsByCategory(posts, "Java");
//    nestedCollectors(posts);
//}
//
//// Prior to JDK 24 :: How to Group By Category, order by publishedDate and limit to 3 most recent posts
//void nestedCollectors(List<BlogPost> posts) {
//
//    Map<String, List<BlogPost>> recentPostsByCategory = posts.stream()
//            // First, group all posts by category
//            .collect(Collectors.groupingBy(
//                    BlogPost::category,
//                    Collectors.collectingAndThen(
//                            // Collect posts into a list
//                            Collectors.toList(),
//                            // Then transform each list by sorting and limiting
//                            categoryPosts -> categoryPosts.stream()
//                                    .sorted(Comparator.comparing(BlogPost::publishedDate).reversed())
//                                    .limit(5)
//                                    .toList()
//                    )
//            ));
//
//    printRecentPostsByCategory(recentPostsByCategory);
//}
//
//void printRecentPostsByCategory(Map<String, List<BlogPost>> recentPostsByCategory){
//    System.out.println("Recent Posts By Category:");
//    recentPostsByCategory.forEach((category, categoryPosts) -> {
//        System.out.println("\nCategory: " + category);
//        categoryPosts.forEach(post -> System.out.println("  - " + post.title() + " (Published: " + post.publishedDate() + ")"));
//    });
//}
//
//void postsByCategory(List<BlogPost> posts, String category) {
//
//    List<BlogPost> postsByCategory = posts.stream()
//            .filter(p -> p.category().equals(category))
//            .sorted(Comparator.comparing(BlogPost::publishedDate).reversed())
//            .limit(5)
//            .toList();
//
//    println("Posts by category : " + category);
//    postsByCategory.forEach(p -> {
//        println(p.title() + " | "+p.publishedDate().toString());
//    });
//}