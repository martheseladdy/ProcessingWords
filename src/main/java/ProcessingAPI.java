public class ProcessingAPI {
    public Results processingRequest(String fileContent){
        WordProcessor processor = new WordProcessor();
        Results processorResults = processor.compute(fileContent);
        return processorResults;
    }
}
