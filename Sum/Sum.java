public class Sum {
    public static void main (String[] args) {
        String[] arg; Long sum = 0L;
        
//         for (int i=0; i<args.length; i++) {
//             arg = arg.concat(" " + args[i]);
//         }
//         args = arg.strip().split("\\s+");
//         for (int i=0; i<args.length; i++) {
//             sum += Integer.parseInt(args[i]);
//         }
        for (int i=0; i<args.length; i++) {
            arg = args[i].strip().split("\\s+");
            for (int j=0; j<arg.length; j++) {
                if (!arg[j].isBlank()){
                    sum += Long.parseLong(arg[j]);
                }
            }
        }
        System.out.println(sum);
    }
}

        
