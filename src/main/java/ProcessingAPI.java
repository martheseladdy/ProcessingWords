public class ProcessingAPI {
    public Results processingRequest(String fileContent){
        //API to handle communications

        WordProcessor processor = new WordProcessor();

        Results processorResults = processor.compute(fileContent);

        return processorResults;
    }
}
