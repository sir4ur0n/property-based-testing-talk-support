namespace Samples
{
    using System.Collections.Generic;
    using System.Linq;

    public static class Idempotence
    {
        public static IEnumerable<string> RemoveSmallWords(this IEnumerable<string> words)
        {
            return words.Where(x => !string.IsNullOrEmpty(x)).Select(x => x.Trim()).Where(x => x.Length > 2);
        }
    }
}
