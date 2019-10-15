function [ output_args ] = drawAtsf( inputfolder, filename, outputfolder )
%DRAWATSF Summary of this function goes here
%   Detailed explanation goes here



count = 0;
w = [];
b = [];

bestweight = 0;
bestbenefit = - Inf;
besttour = '';

hold on

fid = fopen(strcat(inputfolder, '/', filename));
disp(filename);

tline = fgetl(fid);
while ischar(tline)
    strline = strtrim(string(tline));
    if startsWith(strline, '#')
        disp(count);
        count = 0;
        disp(strline);
       
        stairs(w, b);
        
%         disp(w);
%         disp(b);
        w = [];
        b = [];
        tline = fgetl(fid);
        continue
    end
    
    if endsWith(strline, 'tour')
        besttour = strline;
        tline = fgetl(fid);
        continue
    end
    
    if strlength(strline) == 0
        tline = fgetl(fid);
        continue
    end
    
    d = str2double(strsplit(strline));
    w = [w, d(1)];
    b = [b, d(2)];
    
    if d(2) > bestbenefit
            bestbenefit = d(2);
            bestweight = d(1);
    end
    
    count = count+1;
    tline = fgetl(fid);
end

disp(count);

fclose(fid);
stairs(w, b);

bestlength = tsplength(besttour);
tl = strcat(algoname(filename), ...
    '<max benefit: ', num2str(bestbenefit), ...
    ', weight:', int2str(bestweight), ...
    ', tour length:', int2str(bestlength), ...
    '>'); 

title(tl);
xlabel('Weight');
ylabel('Benefit');

set(gca,'position',[0.065 0.075 0.93 0.895],'units','normalized')

print(strcat(outputfolder, '/', filename, '.eps'),'-depsc');
% savefig(strcat(outputfolder, '/', filename, '.fig'));

close;


end

