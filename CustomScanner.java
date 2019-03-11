/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package expressParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author JIBOYE Oluwagbemiro Olaoluwa
 * @since 2011
 *
 * Objects of this class scans a string into tokens based on a list of tokenizer
 * values.
 */
public class CustomScanner {

    private String input;
    private String[] tokens;
    /**
     * If true the tokens will be included in the output.
     */
    private boolean includeTokensInOutput;

    public CustomScanner(String input, boolean includeTokensInOutput, String... tokens) {
        this.input = input;
        this.includeTokensInOutput = includeTokensInOutput;
        this.tokens = tokens;
    }

    public CustomScanner(String input, boolean includeTokensInOutput, String[] moreTokens, String... tokens) {
        this.input = input;
        this.includeTokensInOutput = includeTokensInOutput;
        List<String> copier = new ArrayList<String>();
        copier.addAll(Arrays.asList(tokens));
        copier.addAll(Arrays.asList(moreTokens));

        this.tokens = copier.toArray(new String[]{});

    }

    /**
     * A convenience constructor used when there exists more than one array
     * containing the tokenizer data.
     *
     * @param input The input to scan.
     * @param includeTokensInOutput Will allow the splitting tokens to be added
     * to the final scan if this attribute is set to true.
     * @param splitterTokens An array of tokens on which the input is to be
     * split.
     * @param splitterTokens1 A second array of tokens on which the input is to
     * be split.
     * @param splitterTokens2 A second array of tokens..input as a variable
     * argument list... on which the input is to be split.
     *
     */
    public CustomScanner(String input, boolean includeTokensInOutput, String[] splitterTokens, String[] splitterTokens1, String... splitterTokens2) {
        this.input = input;

        List<String> copier = new ArrayList<String>();
        copier.addAll(Arrays.asList(splitterTokens));
        copier.addAll(Arrays.asList(splitterTokens1));
        copier.addAll(Arrays.asList(splitterTokens2));

        this.tokens = copier.toArray(new String[]{});
        this.includeTokensInOutput = includeTokensInOutput;
    }

    public List<String> scan() {

        String in = this.input;

        List<String> parse = new ArrayList<>();

        Arrays.sort(tokens, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        for (int i = 0; i < in.length(); i++) {

            for (String token : tokens) {
                int len = token.length();

                if (len > 0 && i + len <= in.length()) {
                    String portion = in.substring(i, i + len);

                    if (portion.equals(token)) {
                        if (i != 0) {//avoid empty spaces
                            parse.add(in.substring(0, i));
                        }
                        if (includeTokensInOutput) {
                            parse.add(token);
                        }
                        in = in.substring(i + len);
                        i = -1;
                        break;
                    }

                }

            }

        }
        if (!in.isEmpty()) {
            parse.add(in);
        }

        return parse;
    }

    public static void main1(String[] args) {

        String in
                = "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)+"
                + "(28+32+11-9E12+sin(3.2E9/cos(-3))-sinsinh(5)+sinh(8)";

        CustomScannerOld scanner = new CustomScannerOld(in,
                true, "");

        long start = System.nanoTime();

        List<String> scan = scanner.scan();

        long duration = System.nanoTime() - start;

        System.out.println("Old Scanner parse in>>> " + (duration) + " ns");

        System.out.println("Output>>>\n " + scan);

        CustomScanner scanner1 = new CustomScanner(in,
                true, " ", "sinh", "+", "-", ")", "(", "sin", "cos", "/", "-");

        start = System.nanoTime();

        List<String> scan1 = scanner1.scan();

        duration = System.nanoTime() - start;

        System.out.println("New Scanner parse in>>> " + (duration) + " ns");

        System.out.println("Output>>>\n " + scan1);

    }

    public static void main(String[] args) {

        String code = "08054546565";

        CustomScanner customScanner = new CustomScanner(code, true, "0123456789".split(""));

        int sampleSize = 10;

        int n = new Random(System.currentTimeMillis()).nextInt(sampleSize);
        List<String> scan = new ArrayList<>();
        for (int i = 0; i < sampleSize; i++) {
            if (i == n) {
                long start = System.nanoTime();
                scan = customScanner.scan();
                long duration = (System.nanoTime() - start) / sampleSize;

                System.out.println("Scanner parse in>>> " + (duration) + " ns");
                break;
            }

           scan = customScanner.scan();
        }
        System.err.println("scan: "+scan);

    }

}
